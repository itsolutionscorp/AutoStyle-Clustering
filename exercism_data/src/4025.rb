#Public: compare two strings, letter by letter. 
#
#Arguments:
#first, second - Strings passed to this class converted to-->
#first_array, second_array
#fewest - an Integer that is the length of the shorter of first or 
#         second. Extra letters in the longer String are discarded.
#hamming_score - an Integer that counts the number of mismatches
#
#Returns a value that increments for each mismatch.


class Hamming
  def compute(first, second)
    i = 0
    hamming_score = 0
    first.length > second.length ? fewest = second.length: fewest = first.length
    fewest.times do
      first_array = first.split ""
      second_array = second.split ""
      first_array[i] == second_array[i] ? hamming_score : hamming_score += 1
      i+=1 
    end
    return hamming_score
  end
end

 
