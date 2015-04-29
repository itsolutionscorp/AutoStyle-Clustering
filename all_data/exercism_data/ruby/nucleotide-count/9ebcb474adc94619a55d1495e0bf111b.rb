class DNA

  def initialize sequence
    @sequence = sequence.chars
    @sequence.each do |c|
      raise ArgumentError unless base_list.has_key?(c)
    end
  end

  def count search
    raise ArgumentError and return false unless valid_search?(search)
    @sequence.count { |c| search == c }
  end

  def nucleotide_counts
    base_list.map { |key, value|
      {key => @sequence.select{ |c| key == c}.size }
    }.reduce(:merge)
  end

  private

  def valid_search?(search)
    search.empty? || full_list.has_key?(search)
  end

  def base_list
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def full_list
    {'U' => 0}.merge(base_list)
  end
end
