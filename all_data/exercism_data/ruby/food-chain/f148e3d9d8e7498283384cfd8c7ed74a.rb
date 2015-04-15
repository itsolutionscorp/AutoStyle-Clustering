class FoodChainSong
  def verse(num)
    animals = [nil, 'fly', 'spider', 'bird', 'cat', 'dog', 'goat', 'cow', 'horse']
    
    first_line = "I know an old lady who swallowed a #{animals[num]}.\n"
    wriggled = "wriggled and jiggled and tickled inside her"
    unique_lines = [
      '',
      '',
      "It #{wriggled}.\n",
      "How absurd to swallow a bird!\n",
      "Imagine that, to swallow a cat!\n",
      "What a hog, to swallow a dog!\n",
      "Just opened her throat and swallowed a goat!\n",
      "I don't know how she swallowed a cow!\n",
      "She's dead, of course!\n"
    ]
    middle = ""
    last_line = num==8 ? '' : "I don't know why she swallowed the fly. Perhaps she'll die.\n"

    if (2..7).include?(num)
      (num-1).times do |i|
        middle << "She swallowed the #{animals[num-i]} to catch the #{animals[num-i-1]}"
        middle << " that #{wriggled}" if num-i == 3
        middle << ".\n"
      end
    end  
    
    first_line + unique_lines[num] + middle + last_line  
  end

  def verses(num1, num2)
    str = ''
    (num1..num2).each do |i|
      str += self.verse(i) + "\n"
    end
    str
  end

  def sing
    self.verses(1,8)
  end

end
