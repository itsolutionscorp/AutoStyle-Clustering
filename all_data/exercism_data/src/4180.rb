def compute( s1, s2 )
    s1array = s1.split("")
    s2array = s2.split("")
    length = [s1array.length, s2array.length].min
    count = 0
    length.times do |index|
      if s2array[index] != s1array[index]
        count += 1
      end
    end
    count
  end