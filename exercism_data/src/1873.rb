def compute(one, two)
    two = two.split('')
    one.split('').reduce(0) do |memo, letter|
      if letter_two = two.shift and letter != letter_two
        memo = memo + 1
      end
      memo
    end
  end