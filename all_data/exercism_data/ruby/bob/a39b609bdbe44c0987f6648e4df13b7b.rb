require 'minitest/autorun'

def run_bob
  puts "Bob the lackadaisical teenager!"
  loop do
    puts "What's up!"
    response = gets.chomp
    puts bob(response)
  end
end

def bob(response)
  if response =~ /[$?]/ # Any response ending with a question mark
    "Sure"
  elsif response =~ /^[^a-z]*$/ # string is in all caps
    "Woah, chill out!"
  elsif response == "" #Fine. Be that way!
    "Fine. Be that way!"
  else
    "Whatever!"
  end
end

class Test_Bob < MiniTest::Unit::TestCase
  def test_you_say_question_bob_says_sure
    assert_equal "Sure", bob("Whats your name?")
  end
  def test_you_say_ALL_CAPS_bob_says_Woah
    assert_equal "Woah, chill out!", bob("HELLO")
  end
  def test_you_say_nothing_bob_says_Fine
    assert_equal "Fine. Be that way!", bob("")
  end
end
