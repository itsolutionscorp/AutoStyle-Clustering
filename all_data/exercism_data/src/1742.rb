def compute(strandOne, strandTwo)
    count = 0
    arrayStrandOne = strandOne.split("")
    arrayStrandTwo = strandTwo.split("") 

    arrayStrandOne.zip(arrayStrandTwo).each do |element_of_a, element_of_b|
      if !element_of_a.eql? element_of_b
        count = count + 1
      end
    end

    return count
  end