def compute(strand_1, strand_2)
    min_length = [strand_1, strand_2].map(&:length).min
    strand_1.chars.first(min_length).each_with_index
      .inject(0) do |difference, (element, current_index)|
      element != strand_2[current_index] ? difference + 1 : difference
    end
  end