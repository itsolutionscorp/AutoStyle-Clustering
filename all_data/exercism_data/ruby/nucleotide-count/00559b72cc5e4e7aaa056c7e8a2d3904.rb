class DNA

  attr_accessor :data

  def initialize(data)
    @data = data
    accepted_chars = %w(A T C G)
    data.split("").each do |letter| 
      if accepted_chars.include?(letter) == false
        raise ArgumentError
      end
    end
  end

  def count(input)
    accepted_chars = %w(A T C G U)
    if accepted_chars.include?(input) == false
      raise ArgumentError
    end
    data.count(input)
  end

  def nucleotide_counts
    counts = {"A"=>count("A"), 
              "T"=>count("T"), 
              "C"=>count("C"), 
              "G"=>count("G")}
    counts
  end

end
