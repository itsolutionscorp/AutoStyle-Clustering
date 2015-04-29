class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    count(downcased_words)
  end

  private

    def count(enumerable)
      enumerable.each_with_object(Hash.new(0)) \
      do |element, counter|
        counter[element] += 1
      end
    end

    def downcased_words
      @text.downcase.split(/\W+/).tap do |words|
        words.shift if words.first.empty?
      end
    end
end

class PhraseTest < Minitest::Unit::TestCase
  def test_quote
    assert_equal( { 'a' => 1 },
      Phrase.new('"a?"').word_count )
  end

  def test_unicode_byte_order_marker
    assert_equal( { 'a' => 1 },
      Phrase.new("\xEF\xBB\xBFa").word_count )
  end

  def test_whitespace
    assert_equal( { 'a' => 1 },
      Phrase.new(' a ').word_count )
  end
end
