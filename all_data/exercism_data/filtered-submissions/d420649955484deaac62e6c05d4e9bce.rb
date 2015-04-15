def compute(shorter, longer)
    if shorter.size > longer.size
      self.compute(longer, shorter)
    else
      shorter.chars.zip(longer.chars).count { |a,b| a != b }
    end
  end