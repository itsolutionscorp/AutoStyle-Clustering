#!/usr/bin/env ruby

def main()
end

def combine_anagrams(words)
  h = {}
  words.each do |w|
    key = w.downcase.split("").sort.join("")
    h[key] = [] unless h.include?(key)
    h[key].push(w)
  end
  return h.values
end

if __FILE__ == $0
  main()
end
