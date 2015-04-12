def compute(strand, other)
    strand.chars.zip(other.chars).count do |(this, that)|
      this && that && this != that
    end
  end
end