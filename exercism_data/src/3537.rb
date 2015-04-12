class Hamming

  def compute(string1, string2)

    counter = 0

    if string1.length < string2.length
      string = string1
    else
      string = string2
    end

    string.length.times do |index|
      if string1[index] != string2[index]
        counter += 1
      end
    end

    counter

  end

end
