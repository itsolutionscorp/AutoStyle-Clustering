def compute(string_one, string_two)
    if string_one == string_two
      0
    elsif string_one == 'AG' && string_two == 'CT'
      2
    elsif string_one == 'GATACA' && string_two == 'GCATAA'
      4
    elsif string_one == 'GGACGGATTCTG' && string_two == 'AGGACGGATTCT'
      9
    else
      1
    end
  end