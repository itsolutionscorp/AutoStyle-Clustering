class DNA

  def initialize sequence
    @sequence = Sequence.new sequence
    raise ArgumentError and return nil unless @sequence.valid?
  end

  def count search
    raise ArgumentError and return false unless valid_search?(search)
    @sequence.count(search)
  end

  def nucleotide_counts
    Sequence.base_list.map { |key, value|
      {key => @sequence.count(key) }
    }.reduce(:merge)
  end

  private

  def valid_search?(search)
    search.empty? || Sequence.full_list.has_key?(search)
  end

end

class Sequence < String

  def count(search)
    chars.count { |c| search == c }
  end

  def valid?
    gsub(Regexp.new("[#{self.class.base_list.keys.join}]"), '').empty?
  end

  def self.base_list
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def self.full_list
    {'U' => 0}.merge(base_list)
  end
end
