def compute a, b
    a.each_char.zip(b.each_char).count { |arr| arr[0] != arr[1] }
  end