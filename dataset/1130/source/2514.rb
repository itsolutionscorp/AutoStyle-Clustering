def combine_anagrams(words)
	
	anagramForms = Hash.new
	words.each do |word|
			anagramForms[word] = word.downcase.split(//).sort
	end
	
	groups=[]
	anagramForms.hash_revert.each_value do |value|
		groups.push(value) 
	end    
	
	groups	
end

def anagram?(word1,word2)
	word1.downcase.split(//).sort == word2.downcase.split(//).sort
end

class Hash
  def hash_revert # reverts keys and values	
    r = Hash.new {|h,k| h[k] = []}
    each {|k,v| r[v] << k}
    r
  end
end
