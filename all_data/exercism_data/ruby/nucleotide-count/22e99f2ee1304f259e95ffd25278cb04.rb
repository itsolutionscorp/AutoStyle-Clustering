class DNA

  def initialize(input)
    is_RNA? input
    validate_input? input
    @input = input
  end

  def count(input)
    unless nucleotides.include? input
      raise ArgumentError, "No such nucleotide."
    end
    @input.count(input)
  end

  def nucleotide_counts
    @input.split("").each_with_object(dna_hash) do |nucleotides,count|
      count[nucleotides]+=1
    end
  end

  private

  def is_RNA?(input)
    if input.include?('U')
      raise ArgumentError, "Input is RNA, not DNA."
    end
  end

  def validate_input?(input)
    if inputs_are_all_nucleotides?(input)
      raise ArgumentError, "Invalid Data"
    end
  end

  def inputs_are_all_nucleotides?(input)
    input.split("").find do |letter|
      !nucleotides.include? letter
    end
  end

  def nucleotides
    ['A','C','T','G','U']
  end

  def rna_nucleotides
    ['U','A','C','G']
  end

  def dna_hash
    {'A'=>0,'T'=>0,'C'=>0,'G'=>0}
  end

end
