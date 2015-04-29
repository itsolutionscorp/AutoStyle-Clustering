def compute(str1, str2)

    str1.chars.take(str2.length).each_with_index.inject(0) do |counter, string_index|

      if str2[string_index[1]] != string_index[0]
        counter + 1
      else
        counter
      end

    end

  end