require 'pry'
class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    ClippedStrings.new(strand, other_strand).select {|a, b| a != b }.size
  end

end

class ClippedStrings
  include Enumerable

  attr_reader :results, :size

  def initialize(*args)
    @results = clip(args.flatten)
  end

  def clip(strings)
    @size = strings.map(&:size).min
    strings.collect {|str| str.slice(0, size)}
  end

  def each_char
    char_indexes.each {|i| yield chars_at(i)}
  end

  def each(&block)
    each_char &block
  end

  def char_indexes
    (0..(size - 1)).to_a
  end

  def chars_at(i)
    results.collect {|str| str[i]}
  end

end

class ClippedStringsTest < MiniTest::Unit::TestCase

  def test_one_string_does_not_change
    assert_equal 'Nothing happening here', ClippedStrings.new('Nothing happening here').results.first
  end

  def test_two_strings_get_clipped_to_shortest
    assert_equal ['shor', 'long'], ClippedStrings.new('short', 'long').results
  end

  def test_strings_can_be_passed_in_an_array
    assert_equal ['shor', 'long'], ClippedStrings.new(['short', 'long']).results
  end

  def test_many_strings_get_clipped_to_shortest
    expected = ['a1', 'b1', 'c1', 'd1', 'e1']
    args     = ['a1xx', 'b1yyy', 'c1zzzzzz', 'd1', 'e12323232323232323232323232323232323232323232323']
    assert_equal expected, ClippedStrings.new(args).results
  end

  def test_enumeration_yields_parallel_chars
    expected = [%w[C D E], %w[C D E], %w[C D E]]
    assert_equal expected, ClippedStrings.new('CCC', 'DDDD', 'EEEEE').collect {|chars| chars}
  end

end
