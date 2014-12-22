# Thiago Paris Salviato

# input = ['Cars', 'for', 'Potatoes', 'racs', 'Four', 'Scar', 'creams', 'Scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)

  output = []
  
  words.each do |w|
  
    anag = []
    rest = []
    head = w.downcase.scan(/./).sort.join
    regexp = "\\b"+head+"\\b"
    
    words.each do |word|
    
      if Regexp.new(regexp,true) =~ word.downcase.scan(/./).sort.join
        anag = anag + [word]
      else
        rest = rest + [word] 
      end
      
    end
    
    if not output.include? anag
      output << anag
    end

  end
  
  return output
  
end

