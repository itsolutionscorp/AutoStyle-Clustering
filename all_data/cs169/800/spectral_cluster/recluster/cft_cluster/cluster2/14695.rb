def combine_anagrams(words)
  rtn = Array::new

  words.each do |word|
    p word
    wordDowncase = word.downcase
    letters = wordDowncase.split("")


    exist = false
    rtn.each do |rtnAry|
      rl = rtnAry[0].downcase.split("")

      if rl.length == letters.length then
        p rl
        rl.sort!
        letters.sort!

        match = true
        i = 0
        rl.each do |rli|
          p (rli + " " + letters[i])
          
          if rli != letters[i]
            match = false
          end
          i += 1
        end

        if match == true then
          rtnAry << word
          exist = true
        end
      end
    end

    if !exist then

      rtn << [word]
    end



  end

  return rtn
end

