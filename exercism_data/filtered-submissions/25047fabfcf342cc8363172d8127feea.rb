def compute(s, t)
    s.chars.zip(t.chars).reduce(0) do |diff, chars|
      chars.all? && chars[0] != chars[1] ? diff + 1 : diff
    end
  end