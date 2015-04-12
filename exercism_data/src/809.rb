class Hamming
  def compute(first,second)
    diffs = first.each_char.with_index.select do |c,i|
      c != second[i] unless second[i].nil?
    end
    diffs.count
  end
end
