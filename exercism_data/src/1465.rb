def compute(s,t)
    @@count = 0
    s_enum = s.to_s.each_char
    t_enum = t.to_s.each_char

    loop do
      s_char = s_enum.next
      t_char = t_enum.next
      @@count += 1 if s_char != t_char
    end
    @@count
  end