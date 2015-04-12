class Hamming
  def compute(source, target)
    source.chars.zip(target.chars).count do |source_char, target_char|
      source_char != target_char
    end
  end
end