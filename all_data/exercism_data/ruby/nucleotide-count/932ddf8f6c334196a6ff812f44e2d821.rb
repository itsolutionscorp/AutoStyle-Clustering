class DNA
  BASE = ['A', 'C', 'G', 'T']

  def initialize(dna)
    check_argument(dna)
    @dna = dna
  end

  def count(nucleo)
    return 0 if nucleo == 'U'
    check_argument(nucleo)
    @dna.chars.select { |char| char == nucleo }.size
  end

  def nucleotide_counts
    BASE.each_with_object({}) do |char, counts|
      counts[char] = count(char)
    end
  end

  private

  def check_argument(arg)
    raise ArgumentError unless arg.chars.all? { |c| BASE.include? c }
  end
end
