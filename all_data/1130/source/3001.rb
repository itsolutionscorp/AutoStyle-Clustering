# HW1 part three
#
# Michael Sobelman
#
# Anagram
class Anagram
  attr_accessor :sorted

  def initialize(words)
    @sorted = words.group_by { |word| word.chars.map(&:downcase).sort}
  end
  def list
    result = []
    @sorted.each {|k,v| result << v}
    result
  end
end

def combine_anagrams(words)
  Anagram.new(words).list
end

require 'rspec'

describe "Anagram" do

  before do
    @words = %w[ cars for potatoes Racs four scar creams scream] 
    @list = [
        ["cars", "Racs", "scar"], ["for"], ["potatoes"], ["four"], ["creams", "scream"]] 
  end

  it "takes an array and returns the Anagram array" do
    Anagram.new(@words).list.should == @list
  end
end
