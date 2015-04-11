class Integer
  def to_roman
    a2r = { 1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
            100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL',
            10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV',
            1 => 'I' }
    n = self
    a2r.inject('') do |r, (a,ch)|
      r << ch*(n/a)
      n = n.remainder a
      r
    end
  end
end

class String
  def to_arabic
    r2a =    { 'I' => 1, 'V' => 5, 'X' => 10, 'L' => 50,
               'C' => 100, 'D' => 500, 'M' => 1000 }
    chnorm = { 'IV' => 'IIII', 'IX' => 'VIIII',
               'XL' => 'XXXX', 'XC' => 'LXXXX',
               'CD' => 'CCCC', 'CM' => 'DCCCC'}
    n = self
    chnorm.each_pair do |key, val|
      n.gsub!(key, val)
    end
    n.chars.inject(0) {|a,ch| a += r2a[ch] }
  end
end

#==========================
require 'minitest/autorun'
require_relative 'roman'

class RomanTest < MiniTest::Test

  def test_3999
    assert_equal 'MMMCMXCIX', 3999.to_roman
  end

  def test_I
    assert_equal 1, 'I'.to_arabic
  end

  def test_II
    assert_equal 2, 'II'.to_arabic
  end

  def test_IV
    assert_equal 4, 'IV'.to_arabic
  end

  def test_VI
    assert_equal 6, 'VI'.to_arabic
  end

  def test_IX
    assert_equal 9, 'IX'.to_arabic
  end

  def test_LIX
    assert_equal 59, 'LIX'.to_arabic
  end

  def test_CMXI
    assert_equal 911, 'CMXI'.to_arabic
  end

  def test_MMMCMXCIX
    assert_equal 3999, 'MMMCMXCIX'.to_arabic
  end
end
