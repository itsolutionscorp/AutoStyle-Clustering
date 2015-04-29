class Crypto
  attr_reader :plaintext, :cleantext
  def initialize(plaintext)
    @plaintext = plaintext
    @cleantext = clean_plaintext
  end

  def ciphertext
    ct = ''
    squaresize.times do |i|
      ct << plaintext_segments.inject('') do |column_string,segment|
              column_string << (segment[i] || '')
            end
    end
    ct
  end 

  def ciphertext_segments
    segment(ciphertext, 5).join(' ')
  end

#private
  def clean_plaintext
    plaintext.downcase.gsub(/[^a-z0-9]/,'')
  end

  def squaresize
    sq = Math.sqrt(cleantext.size).to_i
    (sq**2 == cleantext.size) ? sq : sq + 1
  end

  def plaintext_segments
    segment(cleantext, squaresize)
  end

  def segment(text, length)
    pattern = Regexp.new(".{1,#{length}}")
    text.scan(pattern)
  end
end

# Tests
require 'minitest/autorun'
require_relative 'crypto'

class CryptoTest < MiniTest::Test

  def test_normalize_strange_characters
    crypto = Crypto.new('s#$%^&plunk')
    assert_equal "splunk", crypto.clean_plaintext
  end

  def test_normalize_with_numbers
    #skip
    crypto = Crypto.new('1, 2, 3 GO!')
    assert_equal "123go", crypto.clean_plaintext
  end

  def test_size_of_small_square
    #skip
    crypto = Crypto.new('1234')
    assert_equal 2, crypto.squaresize
  end

  def test_size_of_slightly_larger_square
    #skip
    crypto = Crypto.new('123456789')
    assert_equal 3, crypto.squaresize
  end

  def test_size_of_non_perfect_square
    #skip
    crypto = Crypto.new('123456789abc')
    assert_equal 4, crypto.squaresize
  end

  def test_plaintext_segments
    #skip
    crypto = Crypto.new('Never vex thine heart with idle woes')
    assert_equal ["neverv", "exthin", "eheart", "withid", "lewoes"], crypto.plaintext_segments
  end

  def test_other_plaintext_segments
    #skip
    crypto = Crypto.new('ZOMG! ZOMBIES!!!')
    assert_equal ["zomg", "zomb", "ies"], crypto.plaintext_segments
  end

  def test_ciphertext
    #skip
    crypto = Crypto.new('Time is an illusion. Lunchtime doubly so.')
    assert_equal "tasneyinicdsmiohooelntuillibsuuml", crypto.ciphertext
  end

  def test_another_ciphertext
    #skip
    crypto = Crypto.new('We all know interspecies romance is weird.')
    assert_equal "wneiaweoreneawssciliprerlneoidktcms", crypto.ciphertext
  end

  def test_normalized_ciphertext
    #skip
    crypto = Crypto.new('Madness, and then illumination.')
    assert_equal 'msemo aanin dninn dlaet ltshu i', crypto.ciphertext_segments
  end

  def test_more_normalized_ciphertext
    #skip
    crypto = Crypto.new('Vampires are people too!')
    assert_equal 'vrela epems etpao oirpo', crypto.ciphertext_segments
  end
end
