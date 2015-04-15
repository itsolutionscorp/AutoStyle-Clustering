  def combine_anagrams(words)
    @hash = Hash.new { |hash, key| hash[key] = [] }
  
    words.each do |w|
        #if exists, delete and increment other key value        
        char_arr = w.downcase.split(//)        
        char_arr.sort!        
        if @hash.has_key?(char_arr.join)  
           (@hash[char_arr.join] ||= []) << w   
        else  
          @hash.merge!(char_arr.join => Array[w])         
        end                
    end 
    return @hash.values.to_a
  end