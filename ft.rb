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

	describe 'book' do
		it 'should create book and two authors' do
			visit 'http://localhost:8080/book/new'
			fill_in('book.name', with: 'How to new bee')
			fill_in('book.author1.name', with: 'Mao1')
			fill_in('book.author1.age', with: '28')
			fill_in('book.author2.name', with: 'Mao2')
			fill_in('book.author2.age', with: '29')
			fill_in('book.author3.name', with: 'Mao3')
			fill_in('book.author3.age', with: '30')
			click_button('submit')
			page.text.must_include("You have create a new Book!!!")
			page.text.must_include('Book Name : How to new bee')
			page.text.must_include('Author Name : Mao1')
			page.text.must_include('Author Age : 28')
			page.text.must_include('Author Name : Mao2')
			page.text.must_include('Author Age : 29')
			page.text.must_include('Author Name : Mao3')
			page.text.must_include('Author Age : 30')
		end
	end
end



