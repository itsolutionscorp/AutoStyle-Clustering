def compute(strand1, strand2)
    zipped = strand1.split("").zip(strand2.split(""))
    zipped.select(&:all?).inject(0) do |memo, ( a, b )|
      memo += 1 if a != b
      memo
    end
  end