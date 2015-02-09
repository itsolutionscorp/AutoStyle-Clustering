class Anagrams

  # Combine anagrams of given array
  def combine_anagrams(givenArray)

    sortedArray = []  # contains sorted elements
    uniqueArray = []  # contains unique elements
    finalArrays = []  # return array

    givenArray.each_with_index do |x, y|  
      sortedArray[y] = x.chars.sort_by(&:downcase).join
    end

    uniqueArray = sortedArray.uniq

    uniqueArray.each_with_index do |i1, j1|
      puts j1.to_s + " " + i1
      workingArray = []
      idx = 0;
      givenArray.each_with_index do |i2,j2|
       if givenArray[j2].chars.sort_by(&:downcase).join == uniqueArray[j1]
         workingArray[idx] = givenArray[j2]
         idx = idx + 1
       end       
      end
      puts workingArray
      finalArrays[j1] = workingArray
    end
    puts
    puts finalArrays
    finalArrays
  end
end
givenArray = ['cars','for','potatoes','racs','four','scar','creams','scream']
c = Anagrams.new
resultArray = c.combine_anagrams(givenArray)
puts 'Results array'
puts resultArray
