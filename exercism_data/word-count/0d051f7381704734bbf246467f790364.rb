# A lightweight implementation of Smalltalk's Bag.
#   It cries out for Enumerable....
class Bag
  attr_reader :data

  def initialize
    @data = Hash.new{ 0 }
  end

  def <<(o)
    data[o] += 1
  end

  def [](o)
    data[o]
  end

  def to_hash
    data
  end

end

#############################################
# solution 2:
#   pros:
#     Existing Words instance can be reused
#     Bag-like collaborator can be injected
#
#   cons:
#     count feels slightly more complicated
#
# I find it comforting that the bag is created
# in the method which mutates it--everything
# happens in plain sight.
#
# Bag is surely more stable than Words but
# I still like injecting it since this
# lets me inject the Mock in WordsTest2.
##############################################
class Words
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def count(str=phrase, bag=Bag.new)
    wordify(str).each {|word| bag << word}
    bag.to_hash
  end

  private
  def wordify(str)
    as_word_chars_and_spaces(str).downcase.split
  end

  def as_word_chars_and_spaces(str)
    str.gsub(/[^\w| ]/, '')
  end

end

class WordsTest2 < MiniTest::Unit::TestCase

  # Test using the real collaborator
  def test_count_new_phrase_in_existing_word_object
    words = Words.new("Don't bother to count this phrase")
    counts =  {"count" => 1, "this" => 1, "phrase" => 1, "instead" => 1}
    assert_equal counts, words.count("Count this phrase, instead")
  end

  # Same test, but now collaborating with a Mock.
  # If the real collaborator was expensive,
  #   this would be cheaper.
  # I have mixed feelings about all of the expectations, but
  #   this *is* the collaborators API.  What's a girl to do?
  def test_by_mocking_the_collaborator
    bag = Minitest::Mock.new
    bag.expect(:<<, nil, ['count'])
    bag.expect(:<<, nil, ['this'])
    bag.expect(:<<, nil, ['phrase'])
    bag.expect(:<<, nil, ['instead'])
    bag.expect(:to_hash, nil)

    words = Words.new("Don't bother to count this phrase")
    words.count("Count this phrase, instead", bag)
  end

end

# #############################################
# # solution 1:
# #   Do the work in the initializer.
# #   pros:
# #     simple
# #   cons:
# #     relies on an invisible side-effect of
# #       add_to_bag changing the contents of bag
# #     can't reuse an existing Words instance.
# #     depends on Bag
# ##############################################
# class Words
#   attr_reader :phrase, :bag
# 
#   def initialize(phrase)
#     @phrase = phrase
#     @bag    = Bag.new
#     add_to_bag
#   end
# 
#   def count
#     bag.to_hash
#   end
# 
#   private
#   def add_to_bag
#     wordify(phrase).each {|word| bag << word}
#   end
# 
#   def wordify(str)
#     as_word_chars_and_spaces(str).downcase.split
#   end
# 
#   def as_word_chars_and_spaces(str)
#     str.gsub(/[^\w| ]/, '')
#   end
# 
# end
# 
