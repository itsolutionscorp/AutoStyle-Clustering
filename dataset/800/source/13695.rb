# sw saas class - homework 1, question 3

def combine_anagrams(words)
# assume words is an array of strings
  out = Array.new
  words.each { |word|
    dex = out.index { |ana| wordsort(word).eql? wordsort(ana[0]) }
    if dex.nil?
      ana = Array.new
      out << ana
      ana << word
    else
      out[dex] << word
    end
  }
  return out
end

def wordsort(word)
  word.downcase.split('').sort
end


# test 

astr1 = ["rats", "book", "and", "star", "DNA", "Arts", "hobo"]
puts astr1.inspect
puts "  combine: " + combine_anagrams(astr1).inspect



