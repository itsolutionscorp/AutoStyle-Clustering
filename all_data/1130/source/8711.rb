#!/usr/bin/env ruby


@debug = false
@test = false


def dups
  inject({}) {|h,v| h[v]=h[v].to_i+1; h}.reject{|k,v| v==1}.keys
end


def sameWord(word1, word2)
  answer = false
  if word1 == word2
    answer = true 
  elsif word1.upcase == word2.upcase 
   answer = true
  end 
  return answer 
end 

def anagram? (word1, word2) # -----------------------------------------
    word1 = word1.upcase
    word2 = word2.upcase
    word1 = word1.split('').sort.join
    word2 = word2.split('').sort.join
    if @debug 
      puts "#{word1}"
      puts "#{word2}"
    end  
    answer = false
    if word1 == word2
      answer = true
    end
    return answer    
end # anagram # -------------------------------------------------------

def pause
  gets.chomp
end  



def combine_anagrams(words)
  returnMe = []
  if @debug
    puts "GIVEN        : #{words}"
    puts "STARTING WITH: #{returnMe}"    
    pause
  end  
  
  # remove any words defined as duplicates from the given array
  words = words.uniq
  if @debug 
    puts "Now using  : #{words}"
    pause
  end
 


  words.each do |word|
    if returnMe.length == 0
      returnMe << [word]
      if @debug
        puts "adding first list with first word into ouptut array"
        puts "OUTPUT ARRAY: #{returnMe}"
        pause
      end   
    else
        
      # check for duplicate word in the list
      returnMe.each do |wordList|
        dup = false
        wordList.each do |eWord|
          puts "comparing #{eWord} and #{word}"
          if sameWord(eWord, word)
            puts "Found SAME word!"
            dup = true
            break
          end #if same   
        end # each eWord
            
        if not dup
          if anagram?(wordList[0], word)
            # add to the existing list
            wordList << word
            break 
          else
            # add as a new list
            returnMe << [word]
            break
          end # if anagram  
        else
          puts "Skipping duplicate word"
        end  # if not dup    
        
        
      end # returnMe.each 
    end # words.each
  end # if returnMe.empty?
  return returnMe
end

if @test
  wList = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'for', 'scream']
  puts "#{anagram?("dasd", "das")}"
  puts "#{combine_anagrams(wList)}"
end
 
