# Course: Sofware Engineering for SaaS
# HW 1: Ruby calisenics
# Part 3: Anagrams 
# Student: Somasundaram Sundaramahalingam

def combine_anagrams(words)
  output = Hash.new()

  words.each {|x| 
    key = x.downcase().split(/\s*/).sort().to_s()
    if(output.has_key?(key)) then
        output[key].push(x)
    else
        output.store(key,Array.new(1,x))
    end
    }
  return output.values
end
  
  
puts combine_anagrams(["Cars","For","potatoes","Racs","Scar","Creams","Scream"])


