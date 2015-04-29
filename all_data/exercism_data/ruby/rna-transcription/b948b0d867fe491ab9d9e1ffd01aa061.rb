class Complement
  @@DNA = 'GCTA'
  @@RNA = 'CGAU'

  def self.of_dna dna
    dna.scan(/./).collect do |char|
      case 
      when (char == 'G')
      	'C'
      when (char == 'C')
      	'G'
      when (char == 'T')
      	'A'
      else
      	'U'
      end
    end .join()
  end

  def self.of_rna rna
    rna.tr(@@RNA, @@DNA)
  end

end
