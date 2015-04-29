class Robot
    @robotNames = []
    def initialize
        def self.name
            if (defined? @robotname)
                return @robotname
            else
                @robotname = ""
                i=0
                while (i<5)
                    if(i==0 || i==1)
                        source = ('A'..'Z').to_a
                        @robotname << source[rand(26)]
                    else
                        @robotname << rand(9).to_s
                    end
                    i+=1
                end
                return @robotname
            end
        end
        def self.reset
            @robotname = ""
                i=0
                while (i<5)
                    if(i==0 || i==1)
                        source = ('A'..'Z').to_a
                        @robotname << source[rand(26)]
                    else
                        @robotname << rand(9).to_s
                    end
                    i+=1
                end
                return @robotname
        end
    end


end
