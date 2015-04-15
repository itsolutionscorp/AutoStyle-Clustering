  def combine_anagrams(words)
   hash = Hash.new
   words.each do |w|
     str = w.downcase
     sort_str = str.split(//).sort.join
     if(hash.has_key?(sort_str)) then
       hash[sort_str] << w      
     else
       hash[sort_str] = Array.new
       hash[sort_str] << w
     end
   end
  
   return hash.values
  end
    