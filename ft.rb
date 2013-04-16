require 'capybara'
require 'capybara/poltergeist'
require 'minitest/spec'
require 'minitest/autorun'

Capybara.default_driver = :poltergeist

describe 'ft' do
	include Capybara::DSL
	
	it 'should open hello world' do
		visit 'http://localhost:8080/hello'
		page.text.must_include('Hello Mao Chao!')
	end

	it 'should open goodbye' do
		visit 'http://localhost:8080/goodbye'
		page.text.must_include("I'm a service Goodbye Mao Chao!")
	end

	it 'should post simple data' do
		visit 'http://localhost:8080/post/new'
		fill_in('name', with: 'Mao Chao')
		fill_in('age', with: '28')
		click_button('submit')
		page.text.must_include('You have submitted following information:')
		page.text.must_include('Name is Mao Chao!')
		page.text.must_include('Age is 28!')
	end

	describe 'author' do
		it 'should render single model' do
			visit 'http://localhost:8080/author/sample'
			page.text.must_include('Author name is Mao Chao.')
		end

		it 'should render multiple models' do
			visit 'http://localhost:8080/author/list'
			page.text.must_include 'There are following authors:'
			page.text.must_include 'Name : Mao Chao'
			page.text.must_include 'Name : Wang Xiaofeng'
		end

		it 'should create author' do
			visit 'http://localhost:8080/author/new'
			fill_in('author.name', with: 'Mao Chao')
			fill_in('author.age', with: '28')
			click_button('submit')
			page.text.must_include('You have create a new author!!!')
			page.text.must_include('Author Name : Mao Chao')
			page.text.must_include('Author Age : 28')
		end
	end

	describe 'simple_book' do
		it 'should create simple_book and author' do
			visit 'http://localhost:8080/simple-book/new'
			fill_in('simpleBook.name', with: 'How to new bee')
			fill_in('simpleBook.author.name', with: 'Mao Chao')
			fill_in('simpleBook.author.age', with: '28')
			click_button('submit')
			page.text.must_include('You have create a new simple book!!!')
			page.text.must_include('Book Name : How to new bee')
			page.text.must_include('Author Name : Mao Chao')
			page.text.must_include('Author Age : 28')
		end
	end

	describe 'simple_book' do
		it 'should create book and two authors' do
			skip 'not implemente yet'
			visit 'http://localhost:8080/simple-book/new'
			fill_in('simpleBook.name', with: 'How to new bee')
			fill_in('simpleBook.author.name', with: 'Mao Chao')
			fill_in('simpleBook.author.age', with: '28')
			click_button('submit')
			page.text.must_include('You have create a new simple book!!!')
			page.text.must_include('Book Name : How to new bee')
			page.text.must_include('Author Name : Mao Chao')
			page.text.must_include('Author Age : 28')
		end
	end
end



