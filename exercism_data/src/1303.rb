class Hamming

  def compute(str1, str2)
    ham = 0
    small_str = str1.length if str1.length < str2.length
    small_str = str2.length if str1.length >= str2.length
    split1 = str1.split("")
    split2 = str2.split("")
    for i in 0..small_str-1
      ham += 1 if split1[i] != split2[i]
    end
    return ham
  end
end
