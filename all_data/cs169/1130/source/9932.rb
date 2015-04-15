#!/usr/bin/env ruby

def combine_anagrams(words)
  @testHash = Hash.new {|h,k| h[k]=[]}
  @anagrams = Array.new
  words.each do |word|
    @test = word.downcase.split("").sort.join("")
    @testHash[@test] << word
  end

  @testHash.each {|k,v| @anagrams.push(v)}
  return @anagrams
end