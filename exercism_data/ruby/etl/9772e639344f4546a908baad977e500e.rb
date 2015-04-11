class ETL
    def self.transform old
        expect = {}
        old.each do |k, array|
            array.each do |key|
                expect[key.downcase] = k
            end
        end
        expect
    end
end
