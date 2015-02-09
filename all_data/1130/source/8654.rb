def is_anagram?(g_st1, g_st2)
  # Get local variables to preserve the original string
  st1 = g_st1.dup
  st2 = g_st2.dup
  st1.downcase!
  st2.downcase!
  ar1 = []; ar2 = []
  st1.each_char { |c| ar1.push c }
  st2.each_char { |c| ar2.push c }
  ar1.sort!
  ar2.sort!
  ar1 == ar2
end

def insert_into_result (result, str)
  bInserted = false
  result.each { |ag| if is_anagram?(ag[0],str); ag.push(str); bInserted = true; end }
  bInserted = bInserted
end

def combine_anagrams(words)
  result = []
  words.each { |w| result.push([w]) unless insert_into_result(result, w) }
  result
end

#print combine_anagrams(['caRs', 'for', 'pOtatoes', 'racs', 'four','scar', 'creams', 'screAm'])