require 'minitest/autorun'
require './Bob.rb'

class TestBob < MiniTest::Unit::TestCase

  def test_when_you_ask_bob_a_question_it_responds_with_sure
    assert_equal "Sure.", Bob.talk_to("How Are You?"), 'Bob Answered your question wrong'
  end

  def test_when_you_tell_bob_something_he_responds_with_whatever
    assert_equal "Whatever", Bob.talk_to("Clean your room Shane."), 'Bob Answered your statement wrong'
  end

  def test_when_you_yell_at_bob_he_responds_with_Whoah_Chill_Out
    assert_equal "Whoah, Chill Out!", Bob.talk_to("CLEAN YOUR ROOM SHANE"), 'Bob Answered your yelling with badness'
  end

  def test_when_you_dont_say_anything_he_responds_with_Fine_be_that_way
    assert_equal "Fine, Be That Way", Bob.talk_to(""), 'You said nothing and bob said something'

  end
end
