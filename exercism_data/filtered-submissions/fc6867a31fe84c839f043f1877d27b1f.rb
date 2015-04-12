class Hamming

  def compute first, second

    first.each_char.with_index.find_all do |chr,i|
      chr != second[i] unless second[i].nil?
    end.size

  end

end
