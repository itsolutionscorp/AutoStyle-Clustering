# To change this template, choose Tools | Templates
# and open the template in the editor.

class Phrase
  
  def initialize(text)
    @text=text.downcase
  end
  
  def word_count
    pom = Hash.new
    punctuationcaust.split(" ").each {|word| 
      pom[word] =  if (pom[word]==nil)
                    1
                   else
                    pom[word]+1
                   end
    }
    pom
  end

  def punctuationcaust
    pure=""
    (0..@text.length).each { |i| 
      if (should_live?(@text[i])) 
        pure.concat(@text[i]) 
      else 
        pure.concat(" ")
      end
    }
    pure    
  end
  
  def should_live?(char)
    if ((char =~ /[a-zA-Z0-9]/)!=nil or char=="'" or char==" ")
      return true
    else
      return false
    end
  end
  
end
