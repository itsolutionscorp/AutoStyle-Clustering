class Fixnum

  def to_roman
    answer = ""
    counter = self

    # To account for thousand's digit
    (counter / 1000).floor.times do
      answer += "M"
      counter -= 1000
    end

    # To account for over 900 in hundred's place
    (counter / 900).floor.times do
      answer += "CM"
      counter -= 900
    end

    # To account for 500 through 800 in the hundred's place
    if counter > 499
      answer += "D"
      counter -= 500
      (counter / 100).floor.times do
        answer += "C"
        counter -= 100
      end
    end
    # To account for 400 in the hundred's place
    (counter / 400).floor.times do
      answer += "CD"
      counter -= 400
    end

    # To account for 100 through 300 in the hundreds place
    (counter / 100).floor.times do
      answer += "C"
      counter -= 100
    end

    # To account for 90 in the ten's place
    (counter / 90).floor.times do
      answer += "XC"
      counter -= 90
    end

    # To account for 50 through 80 in the ten's place
    if counter > 49
      answer += "L"
      counter -= 50
      (counter / 10).floor.times do
        answer += "X"
        counter -= 10
      end
    end

    # To account for 40 in the ten's place
    (counter / 40).floor.times do
      answer += "XL"
      counter -= 40
    end

    # To account for 10 through 30 in the ten's place
    (counter / 10).floor.times do
      answer += "X"
      counter -= 10
    end

    # To account for 9 in the one's place
    (counter / 9).floor.times do
      answer += "IX"
      counter -= 9
    end

    # To account for 5 through 8 in the one's place
    if counter > 4
      answer += "V"
      counter -= 5
      counter.times do
        answer += "I"
        counter -= 1
      end
    end

    # To account for 4 in the one's place
    (counter / 4).floor.times do
      answer += "IV"
      counter -= 4
    end

    # To account for 1 through 3 in the one's place
    counter.times do
      answer += "I"
      counter -= 1
    end
  

    return answer
  end

end
