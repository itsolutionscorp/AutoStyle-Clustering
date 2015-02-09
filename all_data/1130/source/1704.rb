#Assignment #1 Part 3
#___________________________________________________________________
def combine_anagrams (words)

word_hash={}

  words.each do |word|
    id= word.split('')
    id=id.map {|c| c.downcase}.sort.join.downcase
    
    if !word_hash.has_key?(id); word_hash[id]=[word]
    else; word_hash[id] << word
    end
  end
  word_hash.values
end