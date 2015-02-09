# class HomeWork1

  class EmptyArrayError < StandardError ; end

  def combine_anagrams(words)

    raise EmptyArrayError unless words.length > 0
    
    i_prime = 0
    outer_arr = Array.new(0)
    until i_prime == words.length do 
        i = i_prime + 1
        isub = 0

        if words[i_prime] != nil          
          inner_arr = Array.new(0)
          inner_arr[isub] = words[i_prime] 
          isub = isub + 1  
           until i == words.length 
            if words[i] != nil 
               if anagram?(words[i_prime], words[i])
                  inner_arr[isub] = words[i]
                  words[i] = nil
                  isub = isub + 1
               end
            end
            i += 1
          end
          outer_arr << inner_arr
        end
        i_prime += 1
    end    
    return outer_arr
  end 

  def anagram?(w1, w2)
      if w1 != nil and w2 != nil
         return w1.downcase.split('').sort == w2.downcase.split('').sort
      else
         return false
      end
  end
 # end #class 
