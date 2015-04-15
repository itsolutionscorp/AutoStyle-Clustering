class Raindrops
  def self.convert(argh)
    converted = ''
    case 
    when argh % 3 == 0 && argh % 5 == 0 && argh % 7 == 0
      converted = 'PlingPlangPlong'
    when argh % 3 == 0 && argh % 5 == 0
      converted = 'PlingPlang'
    when argh % 3 == 0 && argh % 7 == 0
      converted = 'PlingPlong'
    when argh % 5 == 0 && argh % 7 == 0
      converted = 'PlangPlong'
    when argh % 3 == 0
      converted = 'Pling'
    when argh % 5 == 0
      converted = 'Plang'
    when argh % 7 == 0
      converted = 'Plong'
    else
      return argh.to_s
    end
    converted 
  end
end
