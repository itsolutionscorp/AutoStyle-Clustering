class Complement
  def self.of_dna(a)
    begin
      map = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
      }

      n = ''
      
      a.each_char do |variable|
        n += map[variable]
      end
    rescue
      raise ArgumentError
    end
      return n
  end

  def self.of_rna(a)
    begin
      map = {
        'C' => 'G',
        'G' => 'C',
        'A' => 'T',
        'U' => 'A'
      }

      n = ''
      
      a.each_char do |variable|
        n += map[variable]
      end

    rescue
      raise ArgumentError
    end

      return n
  end
end
