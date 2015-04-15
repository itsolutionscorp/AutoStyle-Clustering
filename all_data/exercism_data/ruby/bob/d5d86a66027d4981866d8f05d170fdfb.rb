require 'minitest/autorun'
require './bob'

class BobTest < MiniTest::Unit::TestCase


	def test_question
		response = "Sure."
		assert_equal(response, Bob.respond('Whatup?'))
	end

	def test_statement
		response = "Whatever."
		assert_equal(response, Bob.respond('Hello.'))
	end

	def test_empty_response
		response = "Fine. Be that way!"
		assert_equal(response, Bob.respond(''))
	end

	def test_shouting
		response = "Woah, chill out!"
		assert_equal(response, Bob.respond('ALL CAPS!'))
	end

end
