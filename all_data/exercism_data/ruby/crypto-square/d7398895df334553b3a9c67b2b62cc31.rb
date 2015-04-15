class Crypto
  CIPHERTXT_SEG_LEN = 5
  attr_reader :plaintext
  def initialize(plaintext)
    @plaintext = clean(plaintext)
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
    segment(ciphertext, CIPHERTXT_SEG_LEN).join(' ')
  end

private
  def clean(text)
    text.downcase.gsub(/[^a-z0-9]/,'')
  end

  def squaresize
    sq = Math.sqrt(plaintext.size).to_i
    (sq**2 == plaintext.size) ? sq : sq + 1
  end

  def plaintext_segments
    segment(plaintext, squaresize)
  end

  def segment(text, segmentlength)
    pattern = Regexp.new(".{1,#{segmentlength}}")
    text.scan(pattern)
  end
end

# ===================
require 'minitest/autorun'
require_relative 'crypto'

class CryptoTest < MiniTest::Test

  def test_one_character_plaintext
    crypto = Crypto.new('a')
    assert_equal 'a', crypto.ciphertext
  end

  def test_2_character_plaintext
    #skip
    crypto = Crypto.new('az')
    assert_equal 'az', crypto.ciphertext
  end

  def test_downcases_plaintext
    #skip
    crypto = Crypto.new('AZ')
    assert_equal 'az', crypto.ciphertext
  end

  def test_removes_non_alphanumeric_characters_from_plaintext
    #skip
    crypto = Crypto.new('@#a# $z%^')
    assert_equal 'az', crypto.ciphertext
  end

# Testing private methods. 
# Skip or remove once these methods' correctness is implied
# by testing the public interface.
  def test_correct_sq_size_for_perfect_square
    #skip
    crypto = Crypto.new('123456789')
    assert_equal 3, crypto.send(:squaresize)
  end

  def test_correct_sq_size_for_non_perfect_square
    #skip
    crypto = Crypto.new('123456789abc')
    assert_equal 4, crypto.send(:squaresize)
  end

  def test_can_segment_plaintext
    #skip
    crypto = Crypto.new('Never vex thine heart with idle woes')
    segments = ["neverv", "exthin", "eheart", "withid", "lewoes"]
    assert_equal segments, crypto.send(:plaintext_segments)
  end
# End testing private methods

  def test_4_character_plaintext
    #skip
    crypto = Crypto.new('abcd')
    assert_equal 'acbd', crypto.ciphertext
  end

  def test_ciphertext_with_longer_plaintext
    #skip
    crypto = Crypto.new('Time is an illusion. Lunchtime doubly so.')
    assert_equal "tasneyinicdsmiohooelntuillibsuuml", crypto.ciphertext
  end

  def test_normalized_ciphertext
    #skip
    crypto = Crypto.new('Madness, and then illumination.')
    assert_equal 'msemo aanin dninn dlaet ltshu i', crypto.ciphertext_segments
  end
end
