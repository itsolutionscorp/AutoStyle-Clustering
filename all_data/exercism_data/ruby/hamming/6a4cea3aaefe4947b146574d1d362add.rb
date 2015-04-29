#!/usr/bin/ruby
#require ‘jcode’
require 'minitest/autorun'
require_relative 'hamming'

class HammingTest < MiniTest::Unit::TestCase
  def test_no_difference_between_identical_strands
    assert_equal 0, Hamming.compute('A', 'A')
  end

  def test_complete_hamming_distance_of_for_single_nucleotide_strand
    skip
    assert_equal 1, Hamming.compute('A','G')
  end

  def test_complete_hamming_distance_of_for_small_strand
    skip
    assert_equal 2, Hamming.compute('AG','CT')
  end

  def test_small_hamming_distance
    skip
    assert_equal 1, Hamming.compute('AT','CT')
  end

  def test_small_hamming_distance_in_longer_strand
    skip
    assert_equal 1, Hamming.compute('GGACG', 'GGTCG')
  end

  def test_ignores_extra_length_on_first_strand_when_longer
    skip
    assert_equal 1, Hamming.compute('AGAGACTTA', 'AAA')
  end

  def test_ignores_extra_length_on_other_strand_when_longer
    skip
    assert_equal 2, Hamming.compute('AGG', 'AAAACTGACCCACCCCAGG')
  end

  def test_large_hamming_distance
    skip
    assert_equal 4, Hamming.compute('GATACA', 'GCATAA')
  end

  def test_hamming_distance_in_very_long_strand
    skip
    assert_equal 9, Hamming.compute('GGACGGATTCTG', 'AGGACGGATTCT')
  end
end

#step 0 declare vars
v1 =""
v2 = ""
array1=[]
array2=[]

#step 1 I can read in argvs
v1 = ARGV[0]
v2 = ARGV[1]

array1 = v1.chars
array2 = v2.chars

#step 3 I need 2 loops for this
x = 0
theHammingDistance = 0
same = 0
while x < v1.length do
if array1[x] == array2[x]
same += 1
else
theHammingDistance += 1
end
x+=1  #move to compare the next pair of letters
end


# step 4 display some output for debugging
puts theHammingDistance
#puts same
