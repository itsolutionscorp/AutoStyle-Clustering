#!/usr/bin/ruby

def combine_anagrams(words)

  dup_words = Array.new(words) 

  index = 0

  len = dup_words.length

  dup_words.each do |t|
    dup_words.push(t.downcase.split(//).sort.join + "_" + index.to_s)
    index = index + 1
    if index == len
      break
    end 
  end
 
  dup_words.slice!(0, dup_words.length/2)
  dup_words.sort!

  last_elem = nil

  ans = Array.new
  sub_ans = Array.new

  dup_words.each do |t|

    /([a-z]+)_(\d+)/ =~ t

    if last_elem == $1
      sub_ans.push(words[$2.to_i])
    else
      if last_elem != nil
        ans.push(sub_ans)
      end

      sub_ans = Array.new
      sub_ans.push(words[$2.to_i])
      last_elem = $1
    end
  end

  if last_elem != nil
    ans.push(sub_ans)
  end

  return ans

end

#ans = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])
#puts ans.flatten
